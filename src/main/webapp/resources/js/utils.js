/**
 * Created by demi
 * on 04.11.15.
 */

/**
 * For building OOP inheritance
 *
 * @param Child
 * @param Parent
 */
function inherit(Child, Parent) {
    var F = function() { };
    F.prototype = Parent.prototype;
    Child.prototype = new F();
    Child.prototype.constructor = Child;
    Child.superclass = Parent.prototype;
}