<template>
  <v-dialog v-model="dialog" max-width="500px">
    <v-form ref = "dsHocSinh" >
    <v-card>
      <v-card-title class="primary">
        <span class="text-h5 white--text">{{formTitle}}</span>
      </v-card-title>

      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="6" sm="6" md="6">
              <v-text-field
                v-model="hocsinh.hoTen"
                :rules="nameRules"
                :counter="40"
                required
                label="Full Name*"
              ></v-text-field>
            </v-col>
            <v-col cols="6" sm="6" md="6">
              <v-select
                v-model="hocsinh.lop.id"
                :items="listClass"
                label="Select Class*"
                item-text="hoTen"
                item-value="id"
                
                :rules="[(v) => !!v || 'Item is required']"
                dense
                outlined
                required
              ></v-select>
            </v-col>
            <v-col cols="6" sm="6" md="6">
              <v-menu
                v-model="menu"
                :close-on-content-click="false"
                :nudge-right="40"
                transition="scale-transition"
                offset-y
                min-width="auto"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="computedDateFormattedMomentjs"
                    label="Birthday*"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker v-model="hocsinh.namSinh" @input="menu = false"></v-date-picker>
              </v-menu>
            </v-col>
            <v-col cols="6" sm="6" md="6">
              <v-text-field
                v-model="hocsinh.queQuan"
                :rules="cityRules"
                required
                label="City*"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" text @click="closeStudent"> Cancel </v-btn>
        <v-btn color="blue darken-1" text @click="saveStudent"> Save </v-btn>
      </v-card-actions>
    </v-card>
    </v-form>
  </v-dialog>
  
</template>
<script>
import moment from "moment";
import axios from "axios";
export default {
  data: () => ({
    dialog: false,
    menu: false,
    modal: false,
    formTitle: null,
    dialogStatus: "",
    hocsinh: {
      id:null,
      hoTen: "",
      lop: {
        id:null
      },
      namSinh: null,
      queQuan: "",
    },
    nameRules: [
      (v) => !!v || "Name is required!",
      (v) => (v && v.length <= 30) || "Name must be lass than 30 characters",
    ],
    cityRules: [
      (v) => !!v || "City is required!",
      (v) => (v && v.length <= 20) || "Name must be lass than 10 characters",
    ],
    listClass: [],
    defaultItem: {
      id:null,
      hoTen: "",
      lop: {
        id:null
      },
      namSinh: null,
      queQuan: null,
    },
    valid: null,
    editedIndex: -1,
    picker: new Date().toISOString().substr(0, 10),
  }),
  created() {
    this.getClass();
  },
  computed: {
    computedDateFormattedMomentjs() {
      return this.hocsinh.namSinh ? moment(this.hocsinh.namSinh).format("DD-MM-YYYY") : "";
    },
  },
  watch: {
     dialog(){
        if(!this.dialog){
          this.$refs.dsHocSinh.resetValidation();
        }
    
  }
  },
  methods: {
    /*test(){
      //console.log(this.editedItem.class)
    },*/

    openModal(val, obj) {
      if (val == "add" && obj == null) {
        this.formTitle = "THÊM HỌC SINH";
        //this.$refs.dsHocSinh.reset();
        this.resetHocSinh();
        
        
      
      
      }
      else if(val == "update" && obj != null){
        this.formTitle = "SỬA HỌC SINH";
        this.hocsinh.id = obj.id;
        this.hocsinh.hoTen = obj.hoTen;
        this.hocsinh.namSinh = obj.namSinh;
        this.hocsinh.lop.id = obj.lop.id;
        this.hocsinh.queQuan = obj.queQuan;
      }
      this.dialog = !this.dialog;
      this.dialogStatus = val;
    },
    getClass() {
      axios.get("http://localhost:8080/lop").then((response) => {
        this.listClass = response.data;
        //console.log(this.listClass)
        //console.log(response.data)
      });
    },
    ThemHocSinh(){
      axios
      .post("http://localhost:8080/hocsinh/them",this.hocsinh)
      .then((res) => {
        //console.log(this.hocsinh);
        //console.log(res.data);
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
    SuaHocSinh(){
      axios
      .put("http://localhost:8080/hocsinh/sua",this.hocsinh)
      .then((res) =>{
        
        this.$emit("refreshTable");
        alert(res.data);
      })
      .catch((error) =>{
        if(error.response){
          alert(error.response.data);
        }
      })
    }
    ,
    resetHocSinh(){
        this.hocsinh.id = null;
        this.hocsinh.hoTen = "";
        this.hocsinh.namSinh = new Date().toISOString().substr(0, 10);
       // this.hocsinh.lop.id = null;
        this.hocsinh.queQuan = "";
    },
    closeStudent(){
      this.dialog = false;
    },
    saveStudent(){
      if(this.$refs.dsHocSinh.validate()){
        if(this.dialogStatus == "add")
        {
          this.ThemHocSinh();
         //console.log("a");
        }
        else if(this.dialogStatus == "update"){
          this.SuaHocSinh();
         //console.log("b");
        }
        this.dialog = false;
      }
      
    }
  },
};
</script>
