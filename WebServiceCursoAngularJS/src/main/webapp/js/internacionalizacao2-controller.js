
/**
 * 
 */
	angular.module('appAngular', [
		'ngRoute', 
		'ngCookies', 
		'pascalprecht.translate'
	])
	
	.config(function ($translateProvider) {
		$translateProvider.translations('en', {
		    TITLE: 'Hello',
		    FOO: 'This is a paragraph.',
		    BUTTON_LANG_EN: 'english',
		    BUTTON_LANG_DE: 'german'
		  });
		  $translateProvider.translations('de', {
		    TITLE: 'Hallo',
		    FOO: 'Dies ist ein Paragraph.',
		    BUTTON_LANG_EN: 'englisch',
		    BUTTON_LANG_DE: 'deutsch'
		  });
		  $translateProvider.preferredLanguage('en');
	})
	
	.controller('Principal',['$scope', '$translate', function($scope, $translate) {
		
		$scope.changeLanguage = function (key) {
		    $translate.use(key);
		  };
		  
	}]) 