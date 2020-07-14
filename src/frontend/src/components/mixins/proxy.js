import axios from "axios";
export const proxy={
    methods : {
        getList(url){
            const list=[]
            const avg=[]
            let count = 0
            axios.get(url)
                .then(({data})=>{
                    alert('data 얻어옴'+data.count)
                    data.avg.forEach(elem=>avg.push(elem))
                    data.list.forEach(elem=>list.push(elem))
                  count = data.count
                    alert(count)
                }).catch(()=>{
                alert('서버 연결 에러')
            })
            return {list,count,avg}
        }
    }
}
