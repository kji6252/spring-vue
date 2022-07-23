<template>
  <div class="home">
    <search-view />
  </div>
</template>

<script>
import SearchView from "@/views/SearchView";
export default {
  name: "HomeView",
  components: {
    SearchView,
  },
};
</script>
