<template>
  <div class="container" style="display: flex; flex-direction: row">
    <div class="col-4">
      <v-card class="mx-auto" max-width="300" tile>
        <v-list shaped>
          <v-subheader> BASIC HTML</v-subheader>
          <v-list-item-group v-model="selectedItem" color="primary">
            <v-list-item v-for="(item, i) in items" :key="i">
              <v-list-item-icon>
                <v-icon v-text="item.icon"></v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title v-text="item.text"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-card>
    </div>

    <div class="col-4">
      <v-card class="mx-auto" max-width="300" tile>
        <v-list shaped>
          <v-subheader> HTML</v-subheader>
          <v-list-item-group v-model="selectedItem" color="primary">
            <v-list-item v-for="(item, i) in items" :key="i">
              <v-list-item-icon>
                <v-icon v-text="item.icon"></v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title v-text="item.text"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-card>
    </div>

    <div class="col-4">
      <v-card class="mx-auto" max-width="300" tile>
        <v-list shaped>
          <v-subheader> BASIC CSS</v-subheader>
          <v-list-item-group v-model="selectedItem" color="primary">
            <v-list-item v-for="(item, i) in items" :key="i">
              <v-list-item-icon>
                <v-icon v-text="item.icon"></v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title v-text="item.text"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-card>
    </div>
  </div>
</template>
<script>
export default {
  data: () => ({
    selectedItem: 1,
    items: [
      { text: "P TAG", icon: "mdi-clock" },
      { text: "A TAG", icon: "mdi-account" },
      { text: "I TAG", icon: "mdi-flag" },
    ],
  }),
};
</script>
<style scoped></style>
