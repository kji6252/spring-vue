<template>
  <form @submit.prevent="doRegister()">
    <label for="username">ID:</label>
    <input
      type="text"
      id="username"
      name="username"
      v-model="v$.username.$model"
      autofocus
    />
    <p v-for="(error, index) of v$.username.$errors" :key="index">
      <strong>{{ error.$validator }}</strong>
      <small> on property</small>
      <strong>{{ error.$property }}</strong>
      <small> says:</small>
      <strong>{{ error.$message }}</strong>
    </p>
    <br />
    <label for="password">PW:</label>
    <input
      type="password"
      id="password"
      name="password"
      v-model="v$.password.$model"
    />
    <p v-for="(error, index) of v$.password.$errors" :key="index">
      <strong>{{ error.$validator }}</strong>
      <small> on property</small>
      <strong>{{ error.$property }}</strong>
      <small> says:</small>
      <strong>{{ error.$message }}</strong>
    </p>
    <label for="password-check">Check PW:</label>
    <input
      type="password"
      id="password-check"
      name="password-check"
      v-model="v$.passwordCheck.$model"
    />
    <p v-for="(error, index) of v$.passwordCheck.$errors" :key="index">
      <strong>{{ error.$validator }}</strong>
      <small> on property</small>
      <strong>{{ error.$property }}</strong>
      <small> says:</small>
      <strong>{{ error.$message }}</strong>
    </p>
    <!--    <small v-if="!samePassword"> 비밀번호가 같지 않습니다. </small>-->
    <input type="submit" value="Register" />
  </form>
</template>

<script>
import { maxLength, minLength, required, sameAs } from "@vuelidate/validators";
import { useVuelidate } from "@vuelidate/core";
import axios from "axios";
export default {
  name: "RegisterView",
  methods: {
    doRegister() {
      const data = {
        username: this.username,
        password: this.password,
      };
      axios
        .post("/api/register", data)
        .then(() => {
          this.$router.push("/login");
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
  data() {
    return {
      username: null,
      password: null,
      passwordCheck: null,
    };
  },
  setup: () => ({ v$: useVuelidate() }),
  validations() {
    return {
      username: { required, minLength: minLength(1), maxLength: maxLength(50) },
      password: { required, minLength: minLength(4), maxLength: maxLength(50) },
      passwordCheck: {
        required,
        minLength: minLength(4),
        maxLength: maxLength(50),
        samePassword: sameAs(this.password),
      },
    };
  },
};
</script>

<style scoped></style>
