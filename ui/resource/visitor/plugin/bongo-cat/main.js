(function (global) {
    define(["text!./show.html", "css!./show.css"], function (pageView) {
        function BongoCatModel(params, componentInfo) {
            var self = this;

        }
        return {
            viewModel: {
                createViewModel: function (params, componentInfo) {
                    return new BongoCatModel(params, componentInfo);
                }
            },
            template: pageView
        };
    });
})(this);
