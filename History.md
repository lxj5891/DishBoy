final LinearLayout gridView = (LinearLayout)deskActivity.findViewById(R.id.actionView);

        TranslateAnimation translateAnimation = new TranslateAnimation(0,100, 0, 0);
        translateAnimation.setDuration(200);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                int top = gridView.getTop();
                int left = gridView.getLeft();
                int width = gridView.getWidth();
                int height = gridView.getHeight();

                gridView.bringToFront();
                gridView.layout(200,200 , 200, 200);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        gridView.startAnimation(translateAnimation);