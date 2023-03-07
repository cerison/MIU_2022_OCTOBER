function sum(){
    var sum = 0;
    for(let  li of arguments){
        sum += li;
    }
    return sum;
}
console.log(sum(1,2,3,4,5,6))