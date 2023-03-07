let str = 'ebebee erdene';
let key = 'e';
let arr = ['me','you','they','we'];

// console.log(IndexOf(str,key));
// console.log(lastIndexOf(str,key));
// console.log(substring(str,2,5));
// console.log(replace(str,"dare","stupid"));
// console.log(split(str,key));

// console.log(push(arr,key));
// console.log(pop(arr));
// console.log(shift(arr));
console.log(unshift(arr,key));
// console.log(slice(arr,1,3));
// console.log(splice(arr,5,1,'he','she','yes'));

function IndexOf(str,key){
    for(let i = 0; i < str.length; i++){
        if(str.charAt(i) == key){
            return i;
        }
    }
    return -1;
}

function lastIndexOf(str,key){
    for(let i = str.length; i >= 0; i--){
        if(str.charAt(i) == key){
            return i;
        }
    }
    return -1;
}


function substring(str,s,d){
    var me = '';
    for(let i = s; i < d; i++){
        me = me + str.charAt(i);
    }
    return me;
}

function replace(str,tarS,newS){
    var ind = str.indexOf(tarS);
    if(ind > 0){
        let me = str.substring(0,ind) + newS + str.substring(ind + tarS.length);
        str = me;
    }
    return str;
}

function split(str,key){
    var me = '';
    var newArr = [];
    for(let i = 0; i < str.length; i++){
        if(str.charAt(i) == key){
            newArr.push(me);
            if(i == str.length-1){newArr.push('');}
            me = '';
        }
        else{
            me = me + str.charAt(i);
            if(i == str.length-1){newArr.push(me);}
        }
    }
    return newArr;
}

function push(arr, key){
    let newArr = arr;
    newArr.splice(arr.length,0,key);
    // for(let i = 0; i < arr.length; i++){
    //     newArr[i] = arr[i];
    // }
    // newArr[arr.length] = key;
    return newArr;
}

function pop(arr){
    let newArr = arr;
    newArr.splice(arr.length-1,1);
    // for(let i = 0; i < arr.length - 1; i++){
    //     newArr[i] = arr[i];
    // }
    return newArr;
}

function slice(arr,st,ds){
    var newArr = [];
    for(let i = st; i < ds; i++){
        newArr.push(arr[i]);
    }
    return newArr;
}

function shift(arr){
    let newArr = arr;
    newArr.reverse();
    newArr.pop();
    newArr.reverse();
    // newArr = [];
    // for(let i = 1; i < arr.length; i++){
    //     newArr[i-1] = arr[i];
    // }
    return newArr;
}

function unshift(arr,val){
    let newArr = arr;
    newArr.reverse();
    newArr.push(val);
    newArr.reverse();
    // newArr = [val];
    // for(let i = 0; i < arr.length; i++){
    //     newArr[i+1] = arr[i];
    // }
    return newArr;
}

function splice(arr,sp,nd,val1,val2,val3){
    var part1 = [];
    var part2 = [];
    part1 = arr.slice(0,sp);
    part2 = arr.slice(sp+nd,arr.length);

    if(val1 != undefined){part1.push(val1);}
    if(val2 != undefined){part1.push(val2);}
    if(val3 != undefined){part1.push(val3);}

    return part1.concat(part2);
}