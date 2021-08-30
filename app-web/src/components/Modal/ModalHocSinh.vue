<template>
  <v-dialog v-model="dialog" max-width="500px">
    <v-card>
      <v-card-title>
        <span class="text-h5"></span>
      </v-card-title>

      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12" sm="6" md="6">
              <v-text-field v-model="editedItem.hoTen" label="Name Student" required></v-text-field>
            </v-col>
            <v-col cols="12" sm="6" md="6">
              <v-select :items="items" v-model="editedItem.lop" label=" Select Class"></v-select>
            </v-col>
            <v-col cols="12" sm="6" md="6">
              <v-menu
                ref="menu1"
                v-model="menu1"
                :close-on-content-click="false"
                transition="scale-transition"
                offset-y
                max-width="290px"
                min-width="auto"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="formatDate"
                    label="Date"
                    hint="MM/DD/YYYY format"
                    persistent-hint
                    prepend-icon="mdi-calendar"
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="editedItem.namSinh"
                  no-title
                  @input="menu1 = false"
                ></v-date-picker>
              </v-menu>
            </v-col>
            <v-col cols="12" sm="6" md="6">
              <v-text-field v-model="editedItem.queQuan" label="City"></v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" text @click="close"> Cancel </v-btn>
        <v-btn color="blue darken-1" text @click="save"> Save </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>
<script>
import moment from "moment";
export default {
  data: () => ({
    date: new Date().toISOString().substr(0, 10),
    menu1: false,
    picker: new Date().toISOString().substr(0, 10),
    dialog: true,
    items: ["10A1", "10A2", "10A3", "10A4"],
    editedItem: {
      hoTen: "",
      lop: 0,
      namSinh: "2001/11/03",
      queQuan: null,
    },
    defaultItem: {
      hoTen: "",
      lop: 0,
      namSinh: null,
      queQuan: null,
    },
    nameRules: [
      (v) => !!v || "Name is required!",
      (v) => (v && v.length <= 30) || "Name must be lass than 30 characters",
    ],
    select: null,
  }),
  created() {
    this.date = moment(new Date()).format("YYYY-MM-DD");
  },

  computed: {
    formatDate() {
      return this.editedItem.namSinh ? moment(this.editedItem.namSinh).format("DD/MM/YYYY") : "";
    },
  },
  watch: {},
  methods: {
    openModal() {
      this.dialog = !this.dialog;
    },
    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.desserts[this.editedIndex], this.editedItem);
      } else {
        this.desserts.push(this.editedItem);
      }
      this.close();
    },

    clear() {
      this.$refs.form.reset();
    },
  },
};
</script>
