<template>
  <div class="hello">
    <h1>블로그 검색</h1>
    <search-form :query="query" />
    <blog-list :blogs="blogs" />
    <page-navigator
      :query="query"
      :current-page="currentPage"
      :total-pages="totalPages"
    />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import SearchForm from "@/components/SearchForm";
import BlogList from "@/components/BlogList";
import PageNavigator from "@/components/PageNavigator";
export default {
  name: "SearchView",
  components: { PageNavigator, BlogList, SearchForm },
  computed: {
    ...mapGetters("searchStore", [
      "blogs",
      "query",
      "currentPage",
      "totalPages",
    ]),
  },
};
</script>
