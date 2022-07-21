<template>
  <form @submit.prevent="doLogin()">
    <label for="username">ID:</label>
    <input
      type="text"
      id="username"
      name="username"
      v-model="username"
      autofocus
    />
    <br />
    <label for="password">PW:</label>
    <input type="password" id="password" name="password" v-model="password" />
    <input type="submit" value="Login" />
    <router-link to="/register">Register</router-link>
  </form>
</template>

<script>
import axios from "axios";
export default {
  name: "Login",
  methods: {
    doLogin() {
      const data =
        "username=" +
        encodeURIComponent(this.username) +
        "&password=" +
        encodeURIComponent(this.password) +
        "&submit=Login";
      axios
        .post("/api/authentication", data, {
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
        })
        .then(() => {
          axios.get("/api/account").then((response) => {
            const account = response.data;
            if (account) {
              this.$store.commit("accountStore/authenticated", account);
              this.$router.push("/");
            } else {
              axios.post("/api/logout");
            }
          });
        });
    },
  },
  data() {
    return {
      username: null,
      password: null,
    };
  },
};
</script>
