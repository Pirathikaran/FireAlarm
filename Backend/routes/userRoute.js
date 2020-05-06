const router = require('Express').Router()
const User = require('../models/user.model')

router.route('/').get((req,res)=>{
    User.find()
        .then(users=>res.json(users))
        .catch((err)=>res.status(400).json("Error:"+err))

})

router.route('/add').post((req,res)=>{
    const userName = req.body.username;
    const password = req.body.password;

    const newUser= new User({
        username:userName,
        password:password
    })

    newUser.save()
        .then(()=>res.json("User Added!"))
        .catch(err=>res.status(400).json("Error:"+err))
})

router.route('/:name').get((req,res)=>{
    User.find({username:req.params.name})
        .then(user=>res.json(user))
        .catch((err)=>res.status(400).json("Err:"+err))
})


module.exports = router