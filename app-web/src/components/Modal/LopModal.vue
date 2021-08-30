<template>
  <v-dialog v-model="dialog" max-width="500px">
    <v-form ref="dsLOP">
      <v-card>
        <v-card-title class="primary">
          <span class="text-h5 white--text">{{ formTitle }}</span>
        </v-card-title>

        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="6" sm="6" md="6">
                <v-text-field
                  v-model="lop.hoTen"
                  :rules="nameRules"
                  required
                  :counter="40"
                  label="Name Class*"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" @click="close" text> Cancel </v-btn>
          <v-btn color="blue darken-1"  @click="save" text> Save </v-btn>
        </v-card-actions>
      </v-card>
    </v-form>
  </v-dialog>
</template>
<script>
import axios from 'axios';
//import moment from "moment";
//import {EventBus} from "@/main.js"
export default {
  data: () => ({
    dialog: false,
    formTitle: "",
    dialogStatus: "",
    lop:{
      id:null,
      hoTen:"",
      siSo: 0,
    },
    editedItem: {
      name: "",
      count: null,
    },
    defaultItem: {
      name: "",
      count: null,
    },
    editedIndex: -1,
    nameRules: [
      (v) => !!v || "Name is required!",
       (v) => (v && v.length <= 5) || "Name must be lass than 10 characters",
    ],
  }),
  watch:{
      dialog(){
        if(!this.dialog){
          this.$refs.dsLOP.resetValidation();
        }
    
  }
  },
  created() {
    //this.getData();
  },
  methods: {
    openModal(val, obj) {
      if (val == "add" && obj == null) {
        this.formTitle = "ADD CLASS";
        this.resetLop();
      } else {
        this.formTitle = "ALTER CLASS";
        this.lop.id = obj.id;
        this.lop.hoTen = obj.hoTen;
        this.lop.siSo = obj.siSo;
      }
      this.dialog = !this.dialog;
      this.dialogStatus = val;
    },
    ThemLop(){
      axios
      .post("http://localhost:8080/lop/them",this.lop)
      .then((res) => {
        alert(res.data);
        this.$emit("refreshTable");
      })
      .catch((error) =>
      {
        if (error.response) {
            alert(error.response.data);
          }
      })
    },
    SuaLop(){
      axios
      .put("http://localhost:8080/lop/sua",this.lop)
      .then((res)=>{
        this.$emit("refreshTable");
        //console.log(this.lop);
        alert(res.data);
      })
      .catch((error) => {
        if(error.response){
          alert(error.response.data)
        }
      });
    }
    ,
    resetLop(){
        this.lop.id = null;
        this.lop.hoTen = "";
        this.lop.siSo = 0;
    },
    save(){
      if(this.$refs.dsLOP.validate())
      {
        if(this.dialogStatus == "add"){
          this.ThemLop();
        }
        else if(this.dialogStatus == "update") {
          this.SuaLop();
        }
        this.dialog = false;
      }
          
    },
    close(){
      this.dialog = false;
    },
  },
};
</script>
