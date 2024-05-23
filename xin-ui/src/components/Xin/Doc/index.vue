<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'xinDoc',
  data() {
    return {
      url: 'https://www.yuque.com/u1024153/icipor/vh4wfl'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
