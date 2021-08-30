<template>
  <v-navigation-drawer v-model="drawer" absolute temporary>
    <v-list-item>
      <v-list-item-avatar>
        <v-img src="https://randomuser.me/api/portraits/men/78.jpg"></v-img>
      </v-list-item-avatar>

      <v-list-item-content>
        <v-list-item-title>John Leider</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-divider></v-divider>

    <v-list dense>
      <v-list-item v-for="item in items" :key="item.title" link :to="item.link">
        <v-list-item-icon>
          <v-icon>{{ item.icon }}</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>
<script>
import { EventBus } from "../main";
export default {
  data() {
    return {
      drawer: null,
      items: [
        { title: "Home", icon: "mdi-home", link: "/" },
        { title: "About", icon: "mdi-facebook", link: "/about" },
        { title: "CardFirst", icon: "mdi-home", link: "/cardfirst" },
        { title: "CardSecond", icon: "mdi-facebook", link: "/cardsecond" },
        { title: "Form", icon: "mdi-yahoo", link: "/form" },
        { title: "DataTable", icon: "mdi-share", link: "/table" },
        { title: "HocSinh", icon: "mdi-account", link: "/hocsinh"},
         { title: "Lop", icon: "mdi-account", link: "/lop"}
      ],
    };
  },

  watch: {
    group() {
      this.drawer = false;
    },
  },
  created() {
    EventBus.$on("NavBarOpen", this.NavBarOpen);
  },
  methods: {
    NavBarOpen() {
      this.drawer = !this.drawer;
    },
  },
};
</script>
