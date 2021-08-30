<template>
<div class="container mt-10">
  <v-data-table
    :headers="headers"
    :items="students"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>
  </div>
</template>
<script>
 export default {
    data () {
      return {
        headers: [
          {
            text: 'Name',
            align: 'start',
            sortable: false,
            value: 'name',
          },
          { text: 'Age', value: 'age' },
          { text: 'Place', value: 'place' },
          { text: 'Birthday', value: 'birthday' },

        ],
        students: [
          {
            name: 'Nguyen Dinh Van',
            age: 21,
            place: 'Bac Ninh',
            birthday: "1994-11-05",
          },
          {
            name: 'Nguyen Dinh Thanh',
            age: 35,
            place: 'Ha Noi',
            birthday: "1991-12-05",
          },
          {
            name: 'Nguyen Thi Lan Anh',
            age: 24,
            place: 'Nam Dinh',
            birthday: "1990-09-01",
          },
          {
            name: 'Pham Thi Huyen',
            age: 21,
            place: 'Nghe An',
            birthday: "1994-11-05",
          },
          {
            name: 'Nguyen Dac Tung',
            age: 15,
            place: 'Hue',
            birthday: "1994-11-05",
          },
          {
            name: 'Ngo Lan Phuong',
            age: 21,
            place: 'Bac Ninh',
            birthday: "1991-11-05",
          },
          {
            name: 'Ngo Xuan Dai',
            age: 21,
            place: 'Lang Son',
            birthday: "1994-11-05",
          },
          {
            name: 'Pham Duc Xuan',
            age: 23,
            place: 'Thai Binh',
            birthday: "1992-11-05",
          },
        ],
      }
    },
  }
</script>