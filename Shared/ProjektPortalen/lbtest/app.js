const express = require('express');
const router = express.Router();
const app = express();

router.get('/', (req,res) => {
    res.send('Hello');
});

app.use('/', router);

app.listen(process.argv[2] || process.env.PORT || 3000, () => {
    console.log(`App is listening at ${process.argv[2] || process.env.PORT || 3000}`);
});