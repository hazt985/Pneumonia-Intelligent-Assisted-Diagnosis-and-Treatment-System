import base64
import os
import urllib.request
import uuid

import torch
from Predictor import *
from PIL import Image

from flask import Flask, jsonify, request
from flask_cors import CORS

import matplotlib.pyplot as plt

import os

os.environ["KMP_DUPLICATE_LIB_OK"]="TRUE"

app = Flask(__name__)
CORS(app)


@app.route('/api/alg/seg')
def seg():  # put application's code here
    model = Predictor(seg_model_path="./model/seg_model.pth", classify_model_path="./model/classify_model.pth")

    img_url = request.args.get('img_url')
    filename = str(uuid.uuid4()) + '.png'
    urllib.request.urlretrieve(img_url, filename)

    seg_img = model.imread(filename, True)
    seg_pred = model.img_segment(seg_img)

    os.remove(filename)

    plt.imsave('./test_seg.png', seg_pred, cmap="gray")
    with open("./test_seg.png", "rb") as image_file:
        return jsonify({
            "img_base64": base64.b64encode(image_file.read()).decode('utf-8')
        })


@app.route('/api/alg/classify')
def classify():  # put application's code here
    model = Predictor(seg_model_path="./model/seg_model.pth", classify_model_path="./model/classify_model.pth")

    img_url = request.args.get('img_url')
    filename = str(uuid.uuid4()) + '.png'
    urllib.request.urlretrieve(img_url, filename)

    img = model.imread(filename, False)
    classify_pred = model.img_classify(img)

    os.remove(filename)

    keys = [model.id_class_dic[i] for i in range(4)]
    s = "".join(["{}: {:.3f}%\n".format(keys[i], classify_pred[i] * 100) for i in range(4)])

    return jsonify({
        "result": s
    })


if __name__ == '__main__':
    app.run(debug=True)
