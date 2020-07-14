<template>
    <MainLayout>
        <template #header>
            <div id="header">
                <h1>신용대출리스트</h1>
               금융감독원의 Open API 및 금융기관으로부터 심의받은 상품 정보를 제공하고 있습니다.</div>
        </template>
        <template #content>
            <div id="content">
<div id="sidebar">
    <div class="my-2">
        <v-row><v-col>
            <v-btn large style="width: 200px">필터 초기화</v-btn>
        </v-col></v-row>
        <v-row><v-col>
                등급별 금리
                <v-select
                        :items="sectors"
                        style="width: 200px"
                ></v-select>
        </v-col></v-row>
        <v-row>
            <v-col>
                희망 대출 금액
                <v-text-field
                        label="만원"
                        single-line
                        hide-details
                        style="width: 200px"
                ></v-text-field>
            </v-col></v-row>
        <v-row>
            <v-col>
                금융권
                <v-checkbox style="height: 15px" v-for="sector of sectors" :key="sector" :label='sector'></v-checkbox>
            </v-col></v-row>
        <v-row>
            <v-col>
                상환방식
                <v-checkbox  style="height: 15px" v-for="way of ways" :key="way" :label='way'></v-checkbox>
            </v-col></v-row>
        <v-row> <v-col>대출유형<br>
            <v-checkbox  style="height: 15px" v-for=" type of  types" :key="type" :label='type'></v-checkbox>
            </v-col></v-row>
        <v-row> <v-col>
                    관심있는 내역을 선택해주세요<br>
                    <v-chip-group
                            column
                            active-class="primary--text"
                    >
                        <v-chip v-for="interest in interest" :key="interest">
                            {{ interest }}
                        </v-chip>
                    </v-chip-group>
            </v-col>
        </v-row>
    </div>
</div>
                <div id="list">
                <v-row id="menu"><v-col> <div>검색결과 {{count}}개</div></v-col>
                    <v-spacer></v-spacer><v-col > <v-text-field
                            id="searchWord"
                            append-icon="mdi-magnify"
                           @click:append="search"
                            label="키워드 검색"
                            single-line
                            hide-details
                            style="width: 200px"
                            @keyup.enter="search"
                    ></v-text-field>
                    </v-col><v-col>
                        <v-select
                            :items="items"
                            v-model="model"
                            @change="sort"
                    >
                    </v-select>
                    </v-col></v-row>
                    <v-simple-table style="float: left">
                        <template v-slot:default>
                            <thead>
                            <tr>
                                <th class="text-left">Logo</th>
                                <th class="text-left">Seq</th>
                                <th class="text-left">finPrdtNm</th>
                                <th class="text-left">korCoNm</th>
                                <th class="text-left">crdtPrdtTypeNm</th>
                                <th class="text-left">평균금리</th>
                                <th class="text-left"></th>
                            </tr>
                            </thead>

                            <tbody>
                            <tr  class="table" v-for="item in list" :key="item.name">
                                <td><img :src='item.logo'/></td>
                                <td>{{ item.loanBaseSeq}}</td>
                                <td>{{ item.finPrdtNm}}</td>
                                <td>{{ item.korCoNm}}</td>
                                <td>{{ item.crdtPrdtTypeNm }}</td>
                                <td>{{ item.crdtGradAvg}}</td>
                                <td><v-btn @click="detail(item.loanBaseSeq)">자세히 보기</v-btn></td>
                            </tr>
                            </tbody>

                        </template>
                    </v-simple-table>
                    <InfiniteLoading @infinite="infiniteHandler" spinner="spiral"  ref="infiniteLoading"> </InfiniteLoading>
            </div>
            </div>
        </template>
        <template #footer></template>
    </MainLayout>
</template>

<script>
    import MainLayout from "../layout/MainLayout";
    import axios from "axios";
     import InfiniteLoading from 'vue-infinite-loading';
     import {mapState} from 'vuex'
    export default {
        components : {MainLayout, InfiniteLoading},
        data(){ return{
        items : ['제공 순','평균 금리 낮은 순', '은행 이름순'],
            model : '제공 순',
            nowPage : 0,
            interest: [
             '무방문' ,'직장인대출','채무통합가능','신속한대출','중금리대출','무서류','대환대출','금리할인','소액대출','바로확인','채무통합','상환기간장기',
                '중도상환수수료면제','당일대출가능','등급별확정금리','햇살론','최대5만원한도','24시간 즉시입금','취금수수료','상환기간3년이내'
            ],
            types : ['일반신용대출','마이너스한도대출','장기카드대출'],
            ways : ['만기일시상환','원리금균등상환','원금균등상환'],
            sectors : ['은행 및 조합','저축은행','보험사','P2P','캐피탈사','카드사'],
        }},

            methods: {
                infiniteHandler($state) {
                    console.log('인피니트 헨들러 작동 대체 왜??')
                    let searchWord = document.getElementById('searchWord').value
                    if (searchWord === '') searchWord = 'null'
                    setTimeout(() => {
                        this.$store.dispatch('loan/infiniteHandler', {
                            cate: 'loans',
                            nowPage: this.nowPage,
                            searchWord: searchWord,
                            order : 'loanBaseSeq'
                        })
                        $state.loaded()
                        this.nowPage += 1
                        if (this.$store.state.loan.list.length == this.$store.state.loan.count) {
                            $state.complete();
                        }
                    }, 1000)

                },

                search() {
                    this.nowPage = 0
                    let searchWord = document.getElementById('searchWord').value
                    if (searchWord === '') searchWord = 'null'
                    this.$store.dispatch('loan/search', {cate: 'loans', nowPage: 0, searchWord: searchWord,  order : 'loanBaseSeq'})
                    this.$refs.infiniteLoading.stateChanger.reset()
                },
                sort(value) {
                    this.nowPage = 0
                    let searchWord = document.getElementById('searchWord').value
                    if (searchWord === '') searchWord = 'null'
                    let order = ''
                    switch (value) {
                        case '제공 순':
                            order = 'loanBaseSeq'
                            break;
                        case '평균 금리 낮은 순':
                            order = 'loanOptions.crdtGradAvg'
                            break;
                        case '은행 이름순' :
                            order = 'korCoNm'
                            break;
                    }
                            this.$store.dispatch('loan/search', {cate: 'loans', nowPage: 0, searchWord: searchWord,   order : order})
                    this.$refs.infiniteLoading.stateChanger.reset()

                },
                detail(value){
                    this.$store.dispatch('loan/detail', {cate: 'loans', loanBaseSeq : value})
                }
            },
            created() {
                this.$store.state.loan.list=[]
                axios.get(`http://localhost:3000/loans/0/null/loanBaseSeq`)
                    .then(({data}) => {
                        this.$store.state.loan.count = data.count
                        data.list.forEach( (elem)=> {
                            this.$store.state.loan.list.push({
                                logo: elem.logo,  loanBaseSeq: elem.loanBaseSeq,finPrdtNm: elem.finPrdtNm.replace('"','').replace('"',''),
                                korCoNm: elem.korCoNm.replace('"','').replace('"',''), crdtPrdtTypeNm :elem.crdtPrdtTypeNm.replace('"','').replace('"',''), crdtGradAvg : elem.crdtGradAvg
                            })
                        })
                    }).catch(() => {
                    alert('서버 연결 에러')
                })

            },
            computed: {
                ...mapState({
                    list: (state) => state.loan.list,
                    count: (state) => state.loan.count
                }),
            }
        }

</script>

<style scoped>
#header {
 padding-top: 50px;
    padding-left: 50px;
    text-align: center;
}
#content{
    width: 1200px;
    margin: 0 auto;
}
    #sidebar {
        height: 100%;
        width: 300px;
        float : left
    }
    #list{
        min-height: 100px;
        width: 900px;
        float : right;
    }

table td{
    height: 200px;
}

</style>