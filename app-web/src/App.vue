<template>
  <v-app>
    <app-bar v-if="this.$router.currentRoute.path !== '/' && this.$router.currentRoute.path !== '/register'"></app-bar>
    <nav-bar></nav-bar>
    <v-main>
      <router-view />
    </v-main>
    <Footer v-if="this.$router.currentRoute.path !== '/'  && this.$router.currentRoute.path !== '/register'" />
    <back-to-top visibleoffset="500">
      <v-btn fab color="green" dark>
        <v-icon>mdi-arrow-up-bold-outline </v-icon>
      </v-btn>
    </back-to-top>
  </v-app>
</template>

<script>
import AppBar from "@/views/AppBar";
import NavBar from "@/views/NavBar";
import Footer from "@/views/Footer";
import BackToTop from "vue-backtotop";

export default {
  name: "App",
  components: {
    AppBar,
    NavBar,
    Footer,
    BackToTop,
  },

  data: () => ({
    //
  }),
};
</script>
<style>
.main-page {
  margin-top: 30px;
}
</style>
