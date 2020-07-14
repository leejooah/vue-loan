import axios from 'axios'
const state ={
    context : 'http://localhost:3000/',
    registerSuccess : false,
    duplication : false,
    fail :false,
    login : localStorage.getItem("login"),
    member :  localStorage.getItem("email"),
    updateSuccess :false,
    updateFail : false,
    deleteSuccess : false,
    deleteFail : false
}
const actions = {
    async register({commit},payload){
        axios.post(state.context+'members/register',payload, {
            authorization: 'JWT fefege..',
            Accept : 'application/json',
            'Content-Type': 'application/json'
        })
            .then(({data})=>{
                commit('register',data)
            })
    },
    async login({commit},payload){
        axios.post(state.context+'members/',payload, {
            authorization: 'JWT fefege..',
            Accept : 'application/json',
            'Content-Type': 'application/json'
        })
            .then(({data})=>{
                commit('login',data)
            })
    },
    async logout({commit}){
       commit('logout')
    },
    async delete({commit},payload){
        axios.delete(state.context+`members/${payload.email}/${payload.password}`)
            .then(({data})=>{
                commit('delete',data)
            })
    },
    async update({commit},payload){
        axios.put(state.context+`members/${payload. updatePassword}`,
                        {email : payload.email, passwd : payload.currentPassword},
                    {authorization: 'JWT fefege..',
                                        Accept : 'application/json',
                                        'Content-Type': 'application/json'
        })
            .then(({data})=>{
                commit('update',data)
            })
    }
}
const mutations = {
    register(state, data) {
        if (data.result === 'true') {
            state.registerSuccess = true
            state.duplication = false
        } else {
            state.duplication = true
        }
    },
    login(state, data) {
        if (data.result === 'true') {
            state.fail = false
            state.login=true
            localStorage.setItem("login", "true")
            state.member = data.member.email
            localStorage.setItem('email', data.member.email)
        } else {
            state.fail = true
        }
    },
    logout(state) {
        state.login = false
        localStorage.removeItem("login")
        state.member = ''
        localStorage.removeItem('email')
    },
    delete(state, data) {
        if (data.deleteResult === 'true') {
            state.deleteSuccess = true
            state.login = false
            localStorage.removeItem("login")
            state.member = ''
            localStorage.removeItem('email')
        } else {
            state.deleteFail = true
        }
    },
    update(state, data) {
        if (data.updateResult === 'true') {state.updateSuccess = true}
        else {state.updateFail = true}
        }
}
const getters = {}
export default {
    name : 'member',
    namespaced : true,
    state,
    actions,
    mutations,
    getters
}