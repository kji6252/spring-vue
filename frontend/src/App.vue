<template>
  <div id="app">
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link v-if="authenticated" to="/favorite-blogs"
        >Favorite Blog</router-link
      >
      |
      <router-link v-if="authenticated" to="/logout">Logout</router-link>
      <router-link v-else to="/login">Login</router-link>
    </nav>
    <router-view />
  </div>
</template>
<script>
import axios from "axios";
export default {
  created() {
    this.fetchAccount();
    this.fetchHotKeywords();
    this.intervalFetchData();
  },
  computed: {
    authenticated() {
      return this.$store.state.accountStore.authenticated;
    },
  },
  methods: {
    fetchHotKeywords() {
      axios
        .get("/api/hot-keywords")
        .then((response) =>
          this.$store.commit("searchStore/hotKeywords", response.data)
        );
    },
    intervalFetchData() {
      setInterval(() => {
        this.fetchHotKeywords();
      }, 10000);
    },
    fetchAccount() {
      axios.get("/api/account").then((response) => {
        const account = response.data;
        if (account) {
          this.$store.commit("accountStore/authenticated", account);
          this.$router.push("/");
        } else {
          axios.post("/api/logout");
        }
      });
    },
  },
};
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
