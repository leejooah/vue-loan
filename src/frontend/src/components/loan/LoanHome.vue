<template>
    <MainLayout>
        <template #header>
            <div id="header"></div>
        </template>
        <template #content>
            <div id="content">
    <h3 id="enter"></h3>
    <v-row>
        <v-col @click="enterMenu(menu)" v-for="menu of menus" :key="menu"
        >
            <v-hover
            >
                <v-card
                        class="mx-auto"
                        height="60"
                        max-width="400"
                >
                    <v-card-text class="font-weight-hard mt-12 text-center subtitle-1">
                        {{menu}}
                    </v-card-text>
                </v-card>
            </v-hover>
        </v-col>

    </v-row>
            </div>


</template>
<template #footer>
</template>
    </MainLayout>
</template>

<script>
    import router from '@/router'
    import MainLayout from "../layout/MainLayout";
    export default {

        name: "loanMain",
        data(){
            return {
                menus : ['주택, 전세자금 대출', '신용대출','p2p/중금리 대출', '자동차 대출']
            }
        },
        components : {MainLayout},
        methods : {
            enterMenu(menu){
                if(menu==='신용대출'){
                router.push("/creditLoanList")
                }else{
                    alert('준비중입니다')
                }
            }
        }
    }
</script>

<style scoped>

#content{
    width: 60%;
    margin-top : 50px;

}
    #header {
        background-color: #26A69A;
        width: 100%;
        height: 200px;

    }


</style>