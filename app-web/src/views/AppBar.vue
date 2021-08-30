<template>
  <div>
    <v-app-bar color="#01579B" app>
      <v-app-bar-nav-icon @click="NavBarOpen" style="color: white"></v-app-bar-nav-icon>
      <v-btn icon style="color: white; margin-right:10px;">
        <v-img src="https://is.vnecdn.net/v101/31/65/11/4116531/assets/images/logo/logo-9.png" width="50"></v-img>
      </v-btn>
      <router-link to="/" style="color: white; text-decoration: none; font-weight: bold;font-family:'Brush Script MT', cursive; cursive;font-style:italic;">
        <v-toolbar-title pl-3>HONDA VIETNAM</v-toolbar-title>
      </router-link>

      
     <v-spacer></v-spacer>
     
      <v-text-field  placeholder="Search..." text style="margin-top:20px" append-icon ="mdi-magnify" filled dense color="white"></v-text-field>

      <router-link to="/" style="color: white; text-decoration: none; font-weight: bold;font-family:'Brush Script MT', cursive; cursive;font-style:italic;">
        <v-toolbar-title pl-3>Login</v-toolbar-title>
      </router-link>
      <router-link to="/register" style="color: white; text-decoration: none; font-weight: bold;font-family:'Brush Script MT', cursive; cursive;font-style:italic;">
        <v-toolbar-title pl-3>Register</v-toolbar-title>
      </router-link>
      <!-- <v-btn icon style="color: black">
        <v-icon>mdi-magnify</v-icon>
      </v-btn> -->
    </v-app-bar>
  </div>
</template>
<script>
import { EventBus } from "../main";
export default {
  methods: {
    NavBarOpen() {
      EventBus.$emit("NavBarOpen");
      // console.log("kkk");
    },
  },
};
</script>
