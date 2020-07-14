import axios from 'axios'
import router from '@/router'
const state = {
    context : 'http://localhost:3000/',
    list : [],
    count : 0,
    detail : {}
}
const actions = {
    async infiniteHandler({commit},payload){
        axios.get(state.context+`${payload.cate}/${payload.nowPage+1}/${payload.searchWord}/${payload.order}`)
            .then(({data})=>{
                commit('infiniteHandler',data)
            })
    },
    async search({commit},payload){
        axios.get(state.context+`/${payload.cate}/${payload.nowPage}/${payload.searchWord}/${payload.order}`)
            .then(({data})=>{
                commit('search',data)
            }).catch(()=>{

        })
    },
    async detail({commit},payload){
        axios.get(state.context+`/${payload.cate}/detail/${payload.loanBaseSeq}`)
            .then(({data})=>{
                commit('detail',data)
            }).catch(()=>{

        })
    }
}
const mutations = {
    search(state,data){
        state.list=[]
        state.count=0
       state.count = data.count
        data.list.forEach( (elem)=> {
            state.list.push({
                logo: elem.logo,  loanBaseSeq: elem.loanBaseSeq,finPrdtNm: elem.finPrdtNm.replace('"','').replace('"',''),
                korCoNm: elem.korCoNm.replace('"','').replace('"',''), crdtPrdtTypeNm :elem.crdtPrdtTypeNm.replace('"','').replace('"',''), crdtGradAvg : elem.crdtGradAvg
            })
        })
    },
    infiniteHandler(state,data){
        data.list.forEach( (elem)=> {
            state.list.push({
                logo: elem.logo,  loanBaseSeq: elem.loanBaseSeq,finPrdtNm: elem.finPrdtNm.replace('"','').replace('"',''),
                korCoNm: elem.korCoNm.replace('"','').replace('"',''), crdtPrdtTypeNm :elem.crdtPrdtTypeNm.replace('"','').replace('"',''), crdtGradAvg : elem.crdtGradAvg
            })
        })
    },
    detail(state,data){
        state.detail={}
            state.detail = {
                logo: data.detail.logo, finPrdtNm: data.detail.finPrdtNm.replace('"','').replace('"',''),
                korCoNm: data.detail.korCoNm.replace('"','').replace('"',''), crdtPrdtTypeNm :data.detail.crdtPrdtTypeNm.replace('"','').replace('"',''), joinWay :data.detail.joinWay.replace('"','').replace('"',''),
                dclsStrtDay : data.detail.dclsStrtDay.replace('"','').replace('"',''), dclsEndDay : data.detail.dclsEndDay.replace('"','').replace('"',''),  crdtLendRateTypeNm : data.detail.crdtLendRateTypeNm.replace('"','').replace('"',''),
                crdtGrad1 : data.detail.crdtGrad1, crdtGrad4 : data.detail.crdtGrad4, crdtGrad5 : data.detail.crdtGrad5, crdtGrad6 : data.detail.crdtGrad6, crdtGrad10 : data.detail.crdtGrad10, crdtGradAvg : data.detail.crdtGradAvg
            }

        sessionStorage.removeItem("seq")
        sessionStorage.setItem("seq", data.detail.loanBaseSeq)
        router.push("/detail")
    }
}
const getters = {}
export default {
    name : 'loan',
    namespaced : true,
    state,
    actions,
    mutations,
    getters
}