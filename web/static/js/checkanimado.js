class AnimatedCheck{
    static animated(){
        let start = 80;
        let mid = 125;
        let end = 210;
        let width = 20;
        let leftX = start;
        let leftY = start;
        let rightX = mid + 2;
        let rightY = mid - 3;
        let animationSpeed = 20;

        let ctx = document.querySelectorAll('canvas')[0].getContext('2d');
        ctx.lineWidth = width;
        ctx.strokeStyle = 'rgba(0, 150, 0, 1)';

        for (let i = start; i < mid; i++) {
            let drawLeft = window.setTimeout(function () {
                ctx.beginPath();
                ctx.moveTo(start, start);
                ctx.lineTo(leftX, leftY);
                ctx.lineCap = 'round';
                ctx.stroke();
                leftX++;
                leftY++;
            }, 1 + (i * animationSpeed) / 3);
        }

        for (let i = mid; i < end; i++) {
            let drawRight = window.setTimeout(function () {
                ctx.beginPath();
                ctx.moveTo(leftX + 2, leftY - 3);
                ctx.lineTo(rightX, rightY);
                ctx.stroke();
                rightX++;
                rightY--;
            }, 1 + (i * animationSpeed) / 3);
        }
    }

}