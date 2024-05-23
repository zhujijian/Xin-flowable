<template>
  <div>
    <svg-icon icon-class="github" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'xinGit',
  data() {
    return {
      url: 'https://gitee.com/tony2y/xin-flowable'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
