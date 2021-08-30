<template>
  <v-container>
    <v-data-table :headers="headers" :items="dsHocSinh" sort-by="calories" class="elevation-1">
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>My Student CRUD</v-toolbar-title>
          <v-divider class="mx-4" inset vertical></v-divider>
          <v-text-field
            label="Search"
            @keyup.enter ="searchName"
            single-line
            hide-details

            v-model="search"
          ></v-text-field>
           <v-col cols="6" sm="6" md="6">
              <v-select
                v-model="lop.id"
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
          <v-btn  icon @click.left="searchName">
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
          <v-btn  icon @click="deleteFillter">
            <v-icon color="red" >mdi-delete</v-icon>
          </v-btn>
          <v-spacer></v-spacer>

          <v-btn class="mx-2" fab dark color="blue" @click="openModal">
            <v-icon dark> mdi-plus </v-icon>
          </v-btn>
        </v-toolbar>
      </template>
      <template v-slot:[`item.actions`]="{ item }">
        <v-icon large secondary class="mr-2" color="teal" @click="openEditModal(item)">
          mdi-pencil
        </v-icon>
        <v-icon large color="red" @click="deleteModal(item)"> mdi-delete </v-icon>
      </template>
      <template v-slot:[`item.namSinh`]="{ item }">
            {{formateDate(item.namSinh)}}
         </template>
    </v-data-table>
   
    <hoc-sinh-modal ref="ModalHS" @refreshTable="refreshTable"></hoc-sinh-modal>
    <DeleteHocSinhModal ref="DeleteModalHS"  @refreshTable="refreshTable"/>
  </v-container>
</template>

<script>
import HocSinhModal from "./Modal/HocSinhModal.vue";
import DeleteHocSinhModal from "./Modal/DeleteHocSinhModal.vue";
import axios from "axios";
import moment from "moment";
//import {EventBus} from "@/main.js"
export default {
  components: {HocSinhModal,DeleteHocSinhModal},
  data: () => ({
    dialog: false,
    search:"",
    listClass: [],
    headers: [
      {
        text: "ID",
        align: "start",
        sortable: true,
        value: "id",
      },
      { text: "Name", value: "hoTen" },
      { text: "Birthday", value: "namSinh" },
      { text: "Class", value: "lop.hoTen" },
      { text: "City", value: "queQuan" },
      { text: "Actions", value: "actions", sortable: false },
    ],
    dsHocSinh: [],
    hocsinh: {
      hoTen: "",
      namSinh: null,
      lop: {
        id: 0
      },
      queQuan:""
    },
    lop:{
      id:0,
      hoTen:""
    }
  }),

  watch: {
    "lop.id"(){
      //console.log(this.searchName())
       this.searchName();
    }
  },
  mounted() {
    //this.searchName();
  },

  created() {
    this.getData();
    this.getClass();
   
  },

  methods: {
    deleteFillter(){
       this.search = "";
       this.lop.id = 0;
       this.getData();
    },
    getData() {
      axios.get("http://localhost:8080/hocsinh").then((response) => {
        this.dsHocSinh = response.data;
        //console.log(response.data)
      });
    },
    getClass() {
      axios.get("http://localhost:8080/lop").then((response) => {
        this.listClass = response.data;
        //console.log(this.listClass)
        //console.log(response.data)
      });
    },
    searchName(){
       axios.get(`http://localhost:8080/hocsinh/timkiem?hoTen=${this.search}&lopID=${this.lop.id}`).then((response) => {
        this.dsHocSinh = response.data;
        //console.log(response.data)
       //console.log("gggg")
       console.log(this.dsHocSinh);
      }).catch(error =>{
        console.log(error);
      })

           
       
    },
    formateDate(date) {
      return date ? moment(date).format("DD-MM-YYYY") : "";
    },
    openModal() {
      let item = null;
      //console.log("kfkdkdkdkd");
      this.$refs.ModalHS.openModal("add", item);
    },
    deleteModal(item){
      this.$refs.DeleteModalHS.deleteModal("delete",item);
    },
    openEditModal(item) {
      this.$refs.ModalHS.openModal("update", item);
    }
    ,
     refreshTable() {
      this.getData();
    },
    
  },
};
</script>
