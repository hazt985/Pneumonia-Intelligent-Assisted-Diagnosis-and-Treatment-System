<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="医生名" prop="name">
        <el-input v-model="formData.name" placeholder="请输入医生名" />
      </el-form-item>
      <el-form-item label="医生年龄" prop="age">
        <el-input v-model="formData.age" placeholder="请输入医生年龄" />
      </el-form-item>
      <el-form-item label="医生性别" prop="gender">
        <el-select v-model="formData.gender" placeholder="请选择医生性别">
          <el-option
            v-for="dict in getIntDictOptions(DICT_TYPE.SYSTEM_USER_SEX)"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="医生编号" prop="mis">
        <el-input v-model="formData.mis" placeholder="请输入医生编号" />
      </el-form-item>
      <el-form-item label="医生职位" prop="job">
        <el-input v-model="formData.job" placeholder="请输入医生职位" />
      </el-form-item>
      <el-form-item label="医生介绍" prop="intro">
        <el-input v-model="formData.intro" type="textarea" placeholder="请输入医生介绍" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>
<script setup lang="ts">
import { getIntDictOptions, DICT_TYPE } from '@/utils/dict'
import { DoctorApi, DoctorVO } from '@/api/demo/doctor'

/** 医生 表单 */
defineOptions({ name: 'DoctorForm' })

const { t } = useI18n() // 国际化
const message = useMessage() // 消息弹窗

const dialogVisible = ref(false) // 弹窗的是否展示
const dialogTitle = ref('') // 弹窗的标题
const formLoading = ref(false) // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用
const formType = ref('') // 表单的类型：create - 新增；update - 修改
const formData = ref({
  id: undefined,
  name: undefined,
  age: undefined,
  gender: undefined,
  mis: undefined,
  job: undefined,
  intro: undefined
})
const formRules = reactive({
  name: [{ required: true, message: '医生名不能为空', trigger: 'blur' }],
  age: [{ required: true, message: '医生年龄不能为空', trigger: 'blur' }],
  gender: [{ required: true, message: '医生性别不能为空', trigger: 'change' }],
  mis: [{ required: true, message: '医生编号不能为空', trigger: 'blur' }],
  job: [{ required: true, message: '医生职位不能为空', trigger: 'blur' }],
  intro: [{ required: true, message: '医生介绍不能为空', trigger: 'blur' }]
})
const formRef = ref() // 表单 Ref

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = t('action.' + type)
  formType.value = type
  resetForm()
  // 修改时，设置数据
  if (id) {
    formLoading.value = true
    try {
      formData.value = await DoctorApi.getDoctor(id)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open }) // 提供 open 方法，用于打开弹窗

/** 提交表单 */
const emit = defineEmits(['success']) // 定义 success 事件，用于操作成功后的回调
const submitForm = async () => {
  // 校验表单
  await formRef.value.validate()
  // 提交请求
  formLoading.value = true
  try {
    const data = formData.value as unknown as DoctorApi.DoctorVO
    if (formType.value === 'create') {
      await DoctorApi.createDoctor(data)
      message.success(t('common.createSuccess'))
    } else {
      await DoctorApi.updateDoctor(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    // 发送操作成功的事件
    emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    name: undefined,
    age: undefined,
    gender: undefined,
    mis: undefined,
    job: undefined,
    intro: undefined
  }
  formRef.value?.resetFields()
}
</script>