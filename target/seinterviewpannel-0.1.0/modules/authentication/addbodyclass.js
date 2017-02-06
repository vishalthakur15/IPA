/* This is the angular module */
angular.module('myApp').factory("myService", function () {
	/* Function calling */
    return {
        sharedObject: { data: 'sponsored_light_blue' }
    };
});