defineClass('ViewController : UIViewController',[], {
    viewWillAppear:function(animated){
        self.super().viewWillAppear(animated);
        self.view().setBackgroundColor(require('UIColor').grayColor());
    },

    testCrash:function () {
        var  array=['0','1'];
        return array[1];
    },
    }
)