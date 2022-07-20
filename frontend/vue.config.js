
module.exports = {
    outputDir: '../src/main/resources/static',
    devServer: {
        port: 3000,
        proxy: 'http://localhost:8080'
    }
}