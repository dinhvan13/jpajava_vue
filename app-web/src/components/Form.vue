<template>
  <div
    class="container d-flex justify-center mt-15"
    style="max-width: 400px; border:1px solid red;"
  >
      
      <v-form ref="form" v-model="valid" lazy-validation>
        <label for="" style="color:grey; padding-left:80px; font-size:25px; font-weight: bold;">Register</label>
        <v-text-field
          v-model="name"
          :counter="30"
          label="Name"
          :rules="nameRules"
          required
        ></v-text-field>

        <v-text-field
          v-model="age"
          label="Age"
          required
          type="number"
          min="0"
          max="100"
          :rules="ageRules"
        ></v-text-field>
        <label for="Sports">My Favorites</label>
        <p >{{ selected }}</p>
        <v-checkbox
          v-model="selected"
          label="Sports"
          value="Sports"
        ></v-checkbox>
        <v-checkbox
          v-model="selected"
          label="Music"
          value="Music"
        ></v-checkbox>
        <v-checkbox
          v-model="selected"
          label="Go Fishing"
          value="Go Fishing"

        ></v-checkbox>
        
        <v-alert :value="alert" v-if="selected == false" color="pink" dark border="top" transition="scale-transition">
          Item is required
        </v-alert>

        <v-text-field v-model="email" label="E-mail" required :rules="emailRules"></v-text-field>
        
        <label>Gender</label><br />
        <v-radio-group v-model="row" row>
          <v-radio label="Nam" value="nam"></v-radio>
          <v-radio label="Nu" value="nu"></v-radio>
        </v-radio-group>
        <v-col class="d-flex" cols="12" sm="6">
          <v-select
            :items="items"
            label="City"
            :rules="[(v) => !!v || 'Item is required']"
            dense
            outlined
          ></v-select>
        </v-col>

        <!-- <label>Birthday </label><br>
    <v-date-picker v-model="picker"
    > -->

        <!-- </v-date-picker> -->

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
              v-model="dateFormatted"
              label="Date"
              hint="MM/DD/YYYY format"
              persistent-hint
              prepend-icon="mdi-calendar"
              v-bind="attrs"
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker v-model="date" no-title @input="menu1 = false"></v-date-picker>
        </v-menu>

        <v-textarea
          v-model="intro"
          autocomplete="intro"
          label="Introduce"
          :rules="introRules"
        ></v-textarea>

        <v-checkbox
          v-model="checkbox"
          label="Do you agree?"
          :rules="[(v) => !!v || 'You must agree to continue!']"
          required
        ></v-checkbox>

        <v-btn class="mr-4" @click="submit"> submit </v-btn>
        <v-btn @click="clear"> clear </v-btn>
      </v-form>

  </div>
</template>
<script>
import moment from "moment";
export default {
  data() {
    return {
      date: new Date().toISOString().substr(0, 10),
      dateFormatted: this.formatDate(new Date().toISOString().substr(0, 10)),
      menu1: false,
      alert: false,
      valid: null,
      name: "",
      nameRules: [
        (v) => !!v || "Name is required!",
        (v) => (v && v.length <= 30) || "Name must be lass than 30 characters",
      ],
      email: "",
      emailRules: [
        (v) => !!v || "Email is required",
        (v) =>
          /^[a-z0-9]+(?!.*(?:\+-{.{2,}))(?:[.+-]{0,1}[a-z0-9])*@gmail.com$/gim.test(v) ||
          "E-mail must be valid",
      ],
      select: null,
      checkbox: false,
      radios: null,
      age: null,
      ageRules: [(v) => (v && v >= 0 && v <= 100) || "Ages must from 0 to 100 "],
      row: "nam",
      selected: [],
      items: ["Ha Noi", "Bac Ninh", "Ho Chi Minh", "Khanh Hoa"],
      intro: "",
      introRules: [
        (v) => !!v || "Intro is required!",
        (v) => (v && v.length <= 150) || "Name must be lass than 150 characters",
      ],
      picker: new Date().toISOString().substr(0, 10),
    };
  },

  created() {
    this.date = moment(new Date()).format("YYYY-MM-DD");
  },

  computed: {
    computedDateFormatted() {
      return this.formatDate(this.date);
    },
  },
  watch: {
    date() {
      this.dateFormatted = this.formatDate(this.date);
      console.log(this.date);
    },
  },

  methods: {
    formatDate(date) {
      if (!date) return null;

      return moment(date).format("DD/MM/YYYY");
    },
    parseDate(date) {
      if (!date) return null;

      return moment(date).format("YYYY-MM-DD");
    },
    clear() {
      this.$refs.form.reset();
    },
    submit() {
      this.$refs.form.validate();
      if(this.selected.length == 0){
        this.alert =!this.alert
      }
    },
  },
};
</script>
<style scoped>
button {
  margin-right: 15px;
}
</style>
