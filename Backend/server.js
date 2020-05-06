const express=require('express');
const cors=require('cors');
const mongoose=require('mongoose');


require('dotenv').config(); 

const app= express();
const port=process.env.PORT || 5001;

app.use(cors());
app.use(express.json());

//connecting to the database using Connection String used as an environment Variable
const uri = process.env.FIREALARM_URI;
mongoose.connect(uri,{useNewUrlParser:true,useCreateIndex:true});

const connection=mongoose.connection;
connection.once('open',()=>{
    console.log("MongoDB connected successfully");
})
//

//Using the Routes for the Project in the Server
const alarmRouter = require('./routes/alarmRoute');
const userRounter = require('./routes/userRoute')
app.use('/alarm',alarmRouter);
app.use('/user',userRounter);
//

app.listen(port,()=>{
    console.log(`Server is runnig on port: ${port}`);
})


