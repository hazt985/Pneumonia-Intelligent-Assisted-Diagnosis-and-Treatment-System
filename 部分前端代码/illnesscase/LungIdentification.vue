<script setup lang="ts">
const isLoading = ref(true)

onMounted(() => {
  isLoading.value = true
  setTimeout(() => {
    isLoading.value = false
  }, 5000)
})
</script>

<template>
  <div v-loading="isLoading" element-loading-text="Loading...">
    <GroupTitle class="mt-0">基本信息</GroupTitle>
    <p class="mb-8">病人：张三的肺部图像识别结果</p>
    <GroupTitle class="mt-0">图片对比</GroupTitle>
    <div class="flex flex-wrap">
      <div class="w-1/2">
        <div class="font-bold">原始图像</div>
        <img
          class="w-100px"
          src="http://test.yudao.iocoder.cn/efc5f358f66494097cefff518622aef2d0b22528b508df7ede202295018aa1d7.png"
        />
      </div>
      <div class="w-1/2">
        <div class="font-bold">识别结果</div>
        <img
          class="w-100px"
          src="http://test.yudao.iocoder.cn/efc5f358f66494097cefff518622aef2d0b22528b508df7ede202295018aa1d7.png"
        />
      </div>
    </div>
    <GroupTitle>诊断结果</GroupTitle>
    <p>疑似新冠肺炎</p>
  </div>
</template>

<style scoped lang="scss"></style>
