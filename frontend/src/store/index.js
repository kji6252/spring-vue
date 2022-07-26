import Vue from "vue";
import Vuex from "vuex";
import { accountStore } from "@/store/account-store";
import { searchStore } from "@/store/search-store";
import { favoriteStore } from "@/store/favorite-store";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: { accountStore, searchStore, favoriteStore },
});
