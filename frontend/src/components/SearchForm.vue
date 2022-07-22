<template>
  <form @submit.prevent="doSearch()">
    <input type="text" id="query" name="query" v-model="query" autofocus />
    <input type="submit" value="검색" />
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "SearchForm",
  props: {
    inputQuery: String,
  },
  created() {
    this.query = this.inputQuery;
  },
  mounted() {
    this.query = this.$attrs.query;
  },
  data() {
    return {
      query: "",
    };
  },
  methods: {
    doSearch() {
      this.$store.commit("searchStore/query", this.query);
      const query = encodeURIComponent(this.query);
      axios
        .get("/api/search?query=" + query)
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
