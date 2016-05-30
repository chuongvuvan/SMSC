/**
 * @author: @AngularClass
 */

const helpers = require('./helpers');
const objectMerge = require('object-merge');
const commonConfig = require('./protractor.common'); // the settings that are common to prod and dev

/**
 * Webpack Constants
 */
const ENV = process.env.NODE_ENV = process.env.ENV = 'ci';

exports.config = objectMerge(commonConfig.config, {
    sauceUser: process.env.SAUCE_USERNAME,
    sauceKey: process.env.SAUCE_ACCESS_KEY,
    sauceSeleniumAddress: 'localhost:4445/wd/hub',

    multiCapabilities: [
        {
            browserName: 'chrome',
            platform: 'OS X 10.11',
            name: "chrome-tests",
            build: process.env.TRAVIS_BUILD_NUMBER,
            'tunnel-identifier': process.env.TRAVIS_JOB_NUMBER,
            shardTestFiles: true,
            maxInstances: 25
        },
        {
            browserName: 'firefox',
            platform: 'OS X 10.11',
            name: "firefox-tests",
            build: process.env.TRAVIS_BUILD_NUMBER,
            'tunnel-identifier': process.env.TRAVIS_JOB_NUMBER,
            shardTestFiles: true,
            maxInstances: 25
        },
        {
            browserName: 'safari',
            platform: 'OS X 10.11',
            name: "safari-tests",
            build: process.env.TRAVIS_BUILD_NUMBER,
            'tunnel-identifier': process.env.TRAVIS_JOB_NUMBER,
            shardTestFiles: true,
            maxInstances: 25
        },
        {
            browserName: 'MicrosoftEdge',
            name: "edge-tests",
            shardTestFiles: true,
            build: process.env.TRAVIS_BUILD_NUMBER,
            'tunnel-identifier': process.env.TRAVIS_JOB_NUMBER
        },
        {
            browserName: 'opera',
            platform: 'OS X 10.11',
            name: "opera-tests",
            build: process.env.TRAVIS_BUILD_NUMBER,
            'tunnel-identifier': process.env.TRAVIS_JOB_NUMBER,
            shardTestFiles: true,
            maxInstances: 25
        } //,*/
        // {
        // 	platformName: 'iOS',
        // 	browserName: '',
        // 	name: "ios-tests",
        // 	app: 'safari',
        // 	deviceName: 'iPhone Simulator',
        // 	shardTestFiles: true,
        // 	build: process.env.TRAVIS_BUILD_NUMBER,
        // 	'tunnel-identifier': process.env.TRAVIS_JOB_NUMBER
        //
        // },
        // {
        // 	platformName: 'Android',
        // 	browserName: 'Browser',
        // 	name: "android-tests",
        // 	deviceName: 'Android Emulator',
        // 	build: process.env.TRAVIS_BUILD_NUMBER,
        // 	'tunnel-identifier': process.env.TRAVIS_JOB_NUMBER
        // }
    ]
});
