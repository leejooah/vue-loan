<template>
    <MainLayout>
        <template #header>
            <div id="header">
                <img :src="detail.logo"><br>
                {{detail.korCoNm}}
            <h1>{{detail.finPrdtNm}}</h1>
            </div>
        </template>
        <template #content>
            <div id="table">
                <tr><td><h3>대출종류</h3></td><td>{{detail.crdtPrdtTypeNm}}</td></tr>
                <tr><td><h3>금리구분</h3></td><td>{{detail.crdtLendRateTypeNm}}</td></tr>
                <tr><td><h3>신용등급별 평균금리</h3></td><td>1등급 : {{detail.crdtGrad1}}<br>4등급 : {{detail.crdtGrad4}}<br>5등급 : {{detail.crdtGrad5}}
                    <br>6등급 : {{detail.crdtGrad6}}<br>10등급 : {{detail.crdtGrad10}}<br>평균: {{detail.crdtGradAvg}}</td></tr>
                <tr><td><h3>가입방식</h3></td><td>{{detail.joinWay}}</td></tr>
                <tr><td><h3>공시 시작일</h3></td><td>{{detail.dclsStrtDay}}</td></tr>
                <tr><td><h3>공시 종료일</h3></td><td>{{detail.dclsEndDay}}</td></tr>

            </div>
        </template>
        <template #footer></template>
    </MainLayout>
</template>

<script>
    import MainLayout from "../layout/MainLayout";
    import {mapState} from 'vuex'
    import axios from 'axios'
    export default {
        components : {MainLayout},
        computed : {
            ...mapState({
                detail : (state)=>state.loan.detail
            })
        },
        created() {
            console.log(sessionStorage.getItem("seq"))
            axios.get(this.$store.state.loan.context+`/loans/detail/`+sessionStorage.getItem("seq"))
            .then(({data})=>{
                this.$store.state.loan.detail = {
                    logo: data.detail.logo, finPrdtNm: data.detail.finPrdtNm.replace('"','').replace('"',''),
                    korCoNm: data.detail.korCoNm.replace('"','').replace('"',''), crdtPrdtTypeNm :data.detail.crdtPrdtTypeNm.replace('"','').replace('"',''), joinWay :data.detail.joinWay.replace('"','').replace('"',''),
                    dclsStrtDay : data.detail.dclsStrtDay.replace('"','').replace('"',''), dclsEndDay : data.detail.dclsEndDay.replace('"','').replace('"',''),  crdtLendRateTypeNm : data.detail.crdtLendRateTypeNm.replace('"','').replace('"',''),
                    crdtGrad1 : data.detail.crdtGrad1, crdtGrad4 : data.detail.crdtGrad4, crdtGrad5 : data.detail.crdtGrad5, crdtGrad6 : data.detail.crdtGrad6, crdtGrad10 : data.detail.crdtGrad10, crdtGradAvg : data.detail.crdtGradAvg
                }
            })
        },

    }
</script>

<style scoped>
    #header{
        width:50%;
        margin:auto auto;
        padding-top: 50px;
    }
    #table {
        width:60%;
        margin: 0 auto;
        padding-top: 100px;
        padding-bottom: 200px
    }
#table tr td{
    padding-top: 30px;
    padding-left: 100px;
}

</style>