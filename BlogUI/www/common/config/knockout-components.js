//https://knockoutjs.com/documentation/component-overview.html
(function (global, ko) {
    //初始界面
    ko.components.register('login-page', {require: '/static/www/visitor/components/login-page/main.js'});
    ko.components.register('layout-visitor', {require: '/static/www/visitor/components/layout/main.js'});
    ko.components.register('layout-user', {require: '/static/www/user/components/layout/main.js'});
    ko.components.register('layout-admin', {require: '/static/www/admin/components/layout/main.js'});

    //公共组件
    ko.components.register('ko-route', {require: '/static/www/common/custom/ko-route/main.js'});
    ko.components.register('drop-down', {require: '/static/www/common/custom/dropdown/main.js'});
    ko.components.register('custom-menu', {require: '/static/www/common/custom/menu/main.js'});
    ko.components.register('shrink-menu', {require: '/static/www/common/custom/shrink-menu/main.js'});
    ko.components.register('custom-card', {require: '/static/www/common/custom/card/main.js'});
    ko.components.register('custom-tree', {require: '/static/www/common/custom/tree/main.js'});

    //特效组件
    ko.components.register('canvas-three', {require: '/static/www/visitor/components/canvas/three/main.js'});
    ko.components.register('bongo-cat', {require: '/static/www/visitor/components/plugin/bongo-cat/main.js'});

    //系统管理组件
    ko.components.register('user-list', {require: '/static/www/admin/components/user-list/main.js'});


})(this, ko);
