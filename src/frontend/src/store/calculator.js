const state = {
    repaymentMethod : '',
    amount : 0,
    interestRate : 0,
    repaymentPeriod : 0,
    result : [],
     totalInterest : 0
}
const actions = {
    async enter({commit},payload){
        commit('enter',payload)
    },
     async init({commit},payload){
         commit('init',payload)
     },
    async add({commit},payload){
         commit('add',payload)
    },
    async calculate({commit},payload){
         commit('calculate',payload)
    },
    async selectRepaymentMethod({commit},payload){
         commit('selectRepaymentMethod',payload)
    }
}
const mutations = {
    enter(state,payload){
        state.result=[]
     state.totalInterest=0
        if (payload.name==='amount'){state.amount=payload.value}
        else if(payload.name==='interestRate'){state. interestRate=payload.value}
        else if(payload.name==='repaymentPeriod'){state.repaymentPeriod=payload.value}
    },
    init(state,payload){
        state.result=[]
        state.totalInterest=0
       if (payload==='amount'){state.amount=0}
       else if(payload==='interestRate'){state. interestRate=0}
       else if(payload==='repaymentPeriod'){state.repaymentPeriod=0}
       else if(payload==='gracePeroid'){state.gracePeroid=0}
    },
    add(state,payload){
        state.result=[]
        state.totalInterest=0
        const add = (a,b)=> a+b
        if(payload.name==='amount'){state.amount = add(Number(state.amount),Number(payload.value))}
        else if(payload.name==='interestRate'){state.interestRate = add(Number(state.interestRate),Number(payload.value))}
        else if(payload.name==='repaymentPeriod'){state.repaymentPeriod = add(Number(state.repaymentPeriod),Number(payload.value))}
    },
    calculate(state){
        if(state.repaymentMethod==='만기일시상환'){
            state.result=[]
            let interest = Math.round(state.amount*(state.interestRate*0.01)/12)
                    state.result.push({repaymentRound : `${1}~${state.repaymentPeriod*12-1}`, interest:interest+'원', principal : 0+'원', total : interest+'원'})
                state.result.push({repaymentRound : state.repaymentPeriod*12, interest:interest+'원', principal :state.amount+'원', total : (Number(interest)+Number(state.amount))+'원'})
            state.totalInterest =Math.round(state.interestRate*state.amount*state.repaymentPeriod*12/1200)
        }
        else if(state.repaymentMethod==='원리금 균등상환'){
            state.result=[]
                let alpha = state.interestRate/1200
                let total = alpha*state.amount*(Math.pow(1+alpha,state.repaymentPeriod*12))/(Math.pow(1+alpha,state.repaymentPeriod*12)-1)
            let interest=0
            let i =0
            let principal=0
            principal =(total-(alpha*state.amount))*(Math.pow(1+alpha,0))
            interest =alpha*(state.amount)
            state.result.push({repaymentRound : i+1, interest:Math.round(interest)+'원', principal : Math.round(principal)+'원', total : Math.round(total)+'원'})
            for (i=state.repaymentPeriod*12/6-1;i<=state.repaymentPeriod*12-1;i+=state.repaymentPeriod*12/6){
                    principal =(total-(alpha*state.amount))*(Math.pow(1+alpha,i))
                    interest =alpha*(state.amount-sigma(1,i))
                    state.result.push({repaymentRound : i+1, interest:Math.round(interest)+'원', principal : Math.round(principal)+'원', total : Math.round(total)+'원'})
                }

            state.totalInterest = Math.round((total*state.repaymentPeriod*12)-state.amount)
            }
        else if(state.repaymentMethod==='원금균등상환'){
            state.result=[]
            let i=0
            let principal=state.amount/(state.repaymentPeriod*12)
            let interest = state.interestRate/1200*state.amount*(1-(i/(state.repaymentPeriod*12)))
            let total = Number(principal)+Number(interest)
            state.result.push({repaymentRound : i+1, interest:Math.round(interest)+'원', principal : Math.round(principal)+'원', total : Math.round(total)+'원'})
            for (i=state.repaymentPeriod*12/6-1;i<=state.repaymentPeriod*12-1;i+=state.repaymentPeriod*12/6){
                let principal=state.amount/(state.repaymentPeriod*12)
                let interest = state.interestRate/1200*state.amount*(1-(i/(state.repaymentPeriod*12)))
                let total = Number(principal)+Number(interest)
                state.result.push({repaymentRound : i+1, interest:Math.round(interest)+'원', principal : Math.round(principal)+'원', total : Math.round(total)+'원'})
            }
            state.totalInterest =Math.round(state.interestRate*state.amount*(state.repaymentPeriod*12+1)/2400)
        }

        function sigma(start,end){
            let k=0
            let result=0
            let alpha = state.interestRate/1200;
            for (k=start; k<=end; k+=1){
                let total = alpha*state.amount*(Math.pow(1+alpha,state.repaymentPeriod*12))/(Math.pow(1+alpha,state.repaymentPeriod*12)-1)
                result += (total-alpha*state.amount)*(Math.pow(1+alpha,k-1));
            }
            return result
        }
    },
    selectRepaymentMethod(state,payload){
        state.result=[]
        state.amount = 0
        state.interestRate = 0
        state. repaymentPeriod = 0
        state.repaymentMethod=payload
    },

}
const getters = {}

export default {
    name : 'calculator',
    namespaced : true,
    state,
    actions,
    mutations,
    getters
}