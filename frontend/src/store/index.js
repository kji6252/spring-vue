import Vue from "vue";
import Vuex from "vuex";
import { accountStore } from "./account-store";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    accountStore,
  },
});