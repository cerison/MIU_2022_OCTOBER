// let con = {a:3,c:4};
// let con2 = con;
//  //con = {};
// con.a = 8;
// console.log(con);
// console.log(con2);

// // con.a = 9;
// // console.log(con,con2);
// // con2 = 6;
// // console.log(con,con2);

// var array3 = [1,2,3,4,5];
// var array4 = array3;
//  //array3.length = 0;
// array3[2] = 100;
// //array3 = null;
// console.log(array3);
// console.log(array4);

// var array = [1,2,3,4,5];
// console.log(typeof array); 

// var numbers2 = [1, 12, 2 ,23,77,7,33,5,99,234];
// var numbers3 = numbers2.sort((a, b) => {
//    return a - b;
// });

// console.log(numbers3);

arrayObject();
// minifyObject();
// findOldestPerson();
// findYounger();
// findBetween();

function arrayObject(){
    var arr = [];
    var con = {a:1,b:[1,2],c:"string",d:{x:1,y:2}};
    for(let key in con){
        var val = con[key];
        if(Array.isArray(val)){
            var el = key;
            for(let k of val){
                el += k;
            }
            arr.push(el);
        }
        else if(typeof val == 'object'){
            for(let k in val){
                arr.push(key+k+val[k]);
            }
        }
        else{
            arr.push(key+val);
        }
    }
    console.log(arr);
}

function minifyObject(){
    var arr = [{a:1,b:2},{a:2,b:4}];
    var newArr = [];
    
    for(let el of arr){
        if(el.hasOwnProperty('a')){
            newArr.push({a:el.a});
        }
        // for(let ob in el){
        //     if(ob == 'a'){
        //         newArr.push({a:el[ob]});
        //     }
        // }
    }
    console.log(arr);
    console.log(newArr);
}

function findOldestPerson(){
    var arr = [{name:'Ram',age:20},{name:'Lakshman',age:15}];
    var name = arr[0].name;
    var val = arr[0].age;
    for(let el of arr){
        if(el.age > val){
            name = el.name;
            val = el.age;
        }
    }
    console.log('OldestPerson => '+ name);
}

function findYounger(){
    var arr = [{name:'Ram',age:20},{name:'Lakshman',age:15}];
    var name = arr[0].name;
    var val = arr[0].age;
    for(let el of arr){
        if(el.age < val){
            name = el.name;
            val = el.age;
        }
    }
    console.log('YoungerPerson => '+ name);
}

function findBetween(){
    var arr = [{name:'Ram',age:20},{name:'Lakshman',age:15}];
    var bet = '';
    
    for(let el of arr){
        if(el.age > 14 && el.age < 17){
            bet = el;
        }
    }
    console.log(bet);
}