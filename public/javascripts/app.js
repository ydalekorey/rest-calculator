requirejs.config({
    paths: {
        'jquery': '../lib/jquery/jquery'
    }
});

require(['./forms'],
    function (forms) {
    }
);