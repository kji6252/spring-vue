<template>
  <div class="hello">
    <h1>블로그 검색</h1>
    <div>
      <span><search-form :query="query" /></span>
      <span><hot-keyword /></span>
    </div>

    <blog-list
      :blogs="blogs"
      button-text="블로그 즐겨찾기 추가"
      :button-event="addBlog"
    />
    <page-navigator
      v-if="blogs.length > 0"
      :current-page="currentPage"
      :start-page="1"
      :total-pages="totalPages"
      :page-forward-event="doSearch"
    />
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";
import SearchForm from "@/components/SearchForm";
import BlogList from "@/components/BlogList";
import PageNavigator from "@/components/PageNavigator";
import HotKeyword from "@/components/HotKeyword";
export default {
  name: "SearchView",
  components: { HotKeyword, PageNavigator, BlogList, SearchForm },
  computed: {
    ...mapGetters("searchStore", [
      "blogs",
      "query",
      "currentPage",
      "totalPages",
    ]),
  },
  methods: {
    addBlog(blog) {
      axios
        .post("/api/favorite-blogs", blog)
        .then((response) => {
          console.log(response);
        })
        .catch((err) => console.error(err));
    },
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
