var app=angular.module('app', ['ionic']);
app.run(function($ionicPlatform) {
    $ionicPlatform.ready(function() {
        // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
        // for form inputs).
        // The reason we default this to hidden is that native apps don't usually show an accessory bar, at
        // least on iOS. It's a dead giveaway that an app is using a Web View. However, it's sometimes
        // useful especially with forms, though we would prefer giving the user a little more room
        // to interact with the app.

        if (window.StatusBar) {
            // Set the statusbar to use the default style, tweak this to
            // remove the status bar on iOS or change it to use white instead of dark colors.
            StatusBar.styleDefault();
        }
    });
})
    .controller("super",["$scope",function($scope){

    $scope.images=["data/pDetail/1.png","data/pDetail/2.png","data/pDetail/3.png","data/pDetail/4.png"];

    $scope.imgStyle={"margin-top":"-20%"};
    if(type.indexOf("裤")!=-1){
        $scope.type=1;
        $scope.imgStyle={"margin-top":"-100%"};
    }
    $scope.boxStyle={height:"200px"};

}]).
    directive('imageLoad', [function () {
        return {
            link: function (scope, el, attrs, ctrl) {
                var height= 0,elO=el[0],realHeight;
                el.bind("load",function(){
                    height=elO.height;
                    realHeight=height/2;
                    if(scope.type==1){
                        realHeight=height*2/3;
                    }

                    scope.boxStyle={height:realHeight+"px"};
                });
            }
        }
    }]);