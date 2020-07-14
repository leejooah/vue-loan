<template>
    <MainLayout>
        <template #header>
            <v-card id="header" dark ><h1>금융 계산기</h1>
            </v-card></template>
        <template #content>
            <div id="content"><h2>대출 이자 계산기</h2>
                <div id="vars">
                    <div :style="varName"><h3>상환방식</h3></div>
                    <v-select :items="items"  :style="va" @change="selectRepaymentMethod"
                    ></v-select>

                    <div :style="varName"><h3>대출금액</h3></div>
                    <v-text-field
                            suffix="원"
                            single-line
                            hide-details
                            :style="va"
                            v-model="amount"
                            @change="enterAmount"
                    ></v-text-field>
                    <v-btn :style="buttonSeg" @click="init('amount')">초기화</v-btn>
                    <v-btn :style="buttonSeg" @click="add('amount',100000000)">+1억</v-btn>
                    <v-btn :style="buttonSeg" @click="add('amount',10000000)">+1000만</v-btn>
                    <v-btn :style="buttonSeg" @click="add('amount',1000000)">+100만</v-btn>

                    <div :style="varName"><h3>연 이자율</h3></div>
                    <v-text-field
                            suffix="%"
                            single-line
                            hide-details
                            :style="va"
                            v-model="interestRate"
                            @change="enterInterestRate"
                    ></v-text-field>
                    <v-btn :style="buttonSeg" @click="init('interestRate')">초기화</v-btn>
                    <v-btn :style="buttonSeg" @click="add('interestRate',1)">+1%</v-btn>
                    <v-btn :style="buttonSeg" @click="add('interestRate',2)">+2%</v-btn>
                    <v-btn :style="buttonSeg" @click="add('interestRate',5)">+5%</v-btn>

                    <div :style="varName"><h3>상환기간</h3></div>
                    <v-text-field
                            suffix="년"
                            single-line
                            hide-details
                            :style="va"
                            v-model="repaymentPeriod"
                            @change="enterRepaymentPeriod"
                    ></v-text-field>
                    <v-btn :style="buttonSeg" @click="init('repaymentPeriod')">초기화</v-btn>
                    <v-btn :style="buttonSeg" @click="add('repaymentPeriod',1)">+1년</v-btn>
                    <v-btn :style="buttonSeg" @click="add('repaymentPeriod',2)">+2년</v-btn>
                    <v-btn :style="buttonSeg" @click="add('repaymentPeriod',5)">+5년</v-btn>

                    <v-btn id="button" dark @click="calculate"><h2>계산하기</h2></v-btn>
                </div>
                <div id="result" ><h2>계산결과</h2>
                  {{amount}}원을 {{repaymentPeriod}}년 동안 <br>
                        {{repaymentMethod}}으로 대출을 받았을 때 <br>
                        연 이자율 {{interestRate}}% 기준
                    <v-simple-table>
                        <template v-slot:default>
                            <thead>
                            <tr>
                                <th class="text-left">회차</th>
                                <th class="text-left">이자</th>
                                <th class="text-left">원금</th>
                                <th class="text-left">내야하는 금액</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="item in result" :key="item.repaymentRound">
                                <td>{{ item.repaymentRound }}</td>
                                <td>{{ item.interest }}</td>
                                <td>{{ item.principal }}</td>
                                <td>{{ item.total }}</td>
                            </tr>
                            </tbody>
                        </template>
                    </v-simple-table>
                내야하는 총 이자액은 {{totalInterest}}원 입니다.</div>
                </div>
        </template>
        <template #footer></template>
    </MainLayout>
</template>

<script>
    import MainLayout from "../layout/MainLayout";
    import {mapState} from 'vuex'
    export default {
        components : {MainLayout},
        data(){
            return {
                va :{
                    width: '400px',
                    height: '60px',
                },
                varName : {
                    paddingTop: '50px'
                },
               buttonSeg : {
                    width: '90px'
               },
                items : ['만기일시상환','원리금 균등상환', '원금균등상환'],

            }
        },
        methods : {
            selectRepaymentMethod(d){
                this.$store.dispatch('calculator/selectRepaymentMethod',d)
            },
            enterAmount(value){
                console.log(value)
                this.$store.dispatch('calculator/enter', {name : 'amount',value : value})
            },
            enterInterestRate(value){
                this.$store.dispatch('calculator/enter', {name : 'interestRate',value : value})
            },
            enterRepaymentPeriod(value){
                this.$store.dispatch('calculator/enter', {name : 'repaymentPeriod',value : value})
            },
            calculate(){
                this.$store.dispatch('calculator/calculate')
            },
            init(name){
                this.$store.dispatch('calculator/init', name)
            },
            add(name,value){
                this.$store.dispatch('calculator/add', {name : name, value : value})
            }
        },
        computed : {
            ...mapState({
                amount : state => state.calculator.amount,
                interestRate : state => state.calculator. interestRate,
                repaymentPeriod : state => state.calculator.repaymentPeriod,
                repaymentMethod : state => state.calculator.repaymentMethod,
                result : state=>state.calculator.result,
                totalInterest : state => state.calculator.totalInterest
            })
        }
    }
</script>

<style scoped>
#header{
background-color: #26A69A;
    padding-left: 20% ;
    padding-right: 20%;
    padding-top: 50px;
    padding-bottom: 50px;
}
    #content{
        width: 60%;
        margin: 0 auto;
    }
    #vars{
        float: left;
        padding-bottom: 100px;
        width: 40%
    }
    #button {
        height: 70px; width: 400px; margin-top: 50px;
        background-color: #26A69A;
    }

    #result{
    float:left; width: 500px; min-height: 100px;
        padding-left: 100px;
        padding-bottom: 50px;
    }
</style>