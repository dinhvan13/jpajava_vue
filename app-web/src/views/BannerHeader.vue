<template>
<v-container class="main-page">
  <v-carousel>
    <v-carousel-item
      v-for="(item,i) in items"
      :key="i"
      :src="item.src"
      reverse-transition="fade-transition"
      transition="fade-transition"
    ></v-carousel-item>
  </v-carousel>
  </v-container>
</template>



<script>
  export default {
    data () {
      return {
        items: [
          {
            src: 'https://i.pinimg.com/originals/e5/f6/b7/e5f6b7b6921bd30facd3c67197bc3437.jpg',
          },
          {
            src: 'https://hondaoto5squangninh.net/upload/images/section-5-bg.png',
          },
          {
            src: 'https://giaxeotohonda.com/wp-content/uploads/2018/06/hondacity24.062.jpg',
          },
          {
            src: 'https://www.honda-indonesia.com/honda-sensing/Artboard%2011-80.jpg',
          },
        ],
      }
    },
  }
</script>
