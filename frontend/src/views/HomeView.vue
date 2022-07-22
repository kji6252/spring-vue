<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
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
