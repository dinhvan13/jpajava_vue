<template>
<div>
  <BannerHeader/>
  <BannerIntro/>
  <Content/>
</div>
  
</template>

<script>

import BannerHeader from "./BannerHeader.vue";
import BannerIntro from "./BannerIntro.vue";
import Content from "@/components/Content.vue"


export default {
  name: "Home",

  components: {
    BannerHeader,
    BannerIntro,
    Content,
  },
};
</script>
