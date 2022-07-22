<template>
  <div v-if="currentPage > 0">
    <span v-if="isPrevPage"
      ><button @click="doSearch(prevPage)">{{ prevPage }}</button></span
    >
    <span>{{ currentPage }}</span>
    <span v-if="isNextPage"
      ><button @click="doSearch(nextPage)">{{ nextPage }}</button></span
    >
    <div>총 {{ totalPages }} 페이지</div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "PageNavigator",
  props: {
    currentPage: Number,
    totalPages: Number,
    query: String,
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
      return this.prevPage > 0;
    },
    isNextPage() {
      return this.nextPage <= this.totalPages;
    },
  },
  methods: {
    doSearch(pageNumber) {
      const query = encodeURIComponent(this.query);
      axios
        .get("/api/search?query=" + query + "&page=" + pageNumber)
        .then((response) => {
          this.$store.commit("searchStore/pageBlogs", response.data);
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
};
</script>

<style scoped></style>
