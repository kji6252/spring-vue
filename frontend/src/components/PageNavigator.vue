<template>
  <div>
    <span v-if="isPrevPage"
      ><button @click="pageForwardEvent(prevPage)">{{ prevPage }}</button></span
    >
    <span>{{ currentPage }}</span>
    <span v-if="isNextPage"
      ><button @click="pageForwardEvent(nextPage)">{{ nextPage }}</button></span
    >
    <div>총 {{ totalPages }} 페이지</div>
  </div>
</template>

<script>
export default {
  name: "PageNavigator",
  props: {
    currentPage: Number,
    startPage: Number,
    totalPages: Number,
    pageForwardEvent: Function,
  },
  created() {
    this.prevPage = this.currentPage - 1;
    this.nextPage = this.currentPage + 1;
  },
  updated() {
    this.prevPage = this.currentPage - 1;
    this.nextPage = this.currentPage + 1;
  },
  data() {
    return {
      nextPage: 0,
      prevPage: 0,
    };
  },
  computed: {
    isPrevPage() {
      return this.prevPage >= this.startPage;
    },
    isNextPage() {
      return this.nextPage < this.totalPages;
    },
  },
};
</script>

<style scoped></style>
