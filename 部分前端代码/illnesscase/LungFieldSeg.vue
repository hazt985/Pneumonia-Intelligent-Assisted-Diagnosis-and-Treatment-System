<script setup lang="ts">
import { useAsyncState } from '@vueuse/core'
import { LungALgApi } from '@/api/demo/alg'

const props = defineProps<{
  imgUrl: string
}>()

const { state: imgBase64, isLoading: segLoading } = useAsyncState(
  () => LungALgApi.seg(props.imgUrl).then((res) => res.data.img_base64),
  ''
)

const { state: result, isLoading: classifyLoading } = useAsyncState(
  () => LungALgApi.classify(props.imgUrl).then((res) => res.data.result),
  ''
)
</script>

<template>
  <div element-loading-text="Loading...">
    <GroupTitle class="mt-0">基本信息</GroupTitle>
    <p class="mb-8">病人：张三的智能识别结果</p>
    <GroupTitle class="mt-0">图片对比</GroupTitle>
    <div class="flex flex-wrap">
      <div class="w-1/2">
        <div class="mb-4 font-bold">原始图像</div>
        <img class="w-[50%]" :src="imgUrl" />
      </div>
      <div class="w-1/2" v-loading="segLoading">
        <div class="mb-4 font-bold">肺野分割结果</div>
        <img class="w-[50%]" :src="`data:image/jpeg;base64,${imgBase64}`" />
      </div>
    </div>
    <GroupTitle>诊断预测结果</GroupTitle>
    <div v-loading="classifyLoading" class="text-1rem whitespace-pre-line" v-html="result"></div>
  </div>
</template>

<style scoped lang="scss"></style>
