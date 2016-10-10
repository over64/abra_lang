; kmMat4* kmMat4PerspectiveProjection(kmMat4* pOut, kmScalar fovY, kmScalar aspect, kmScalar zNear, kmScalar zFar)
declare float* @kmMat4PerspectiveProjection(float*, float, float, float, float)

; kmMat4* kmMat4LookAt(kmMat4* pOut, const kmVec3* pEye, const kmVec3* pCenter, const kmVec3* pUp)
declare void @kmMat4LookAt(%struct.lib.kazmath.Mat4*, %struct.lib.kazmath.Vec3*, %struct.lib.kazmath.Vec3*, %struct.lib.kazmath.Vec3*)

; kmMat4* kmMat4Identity(kmMat4* pOut)
declare void @kmMat4Identity(%struct.lib.kazmath.Mat4*)

; kmMat4* kmMat4RotationZ(kmMat4* pOut, const kmScalar radians)
declare void @kmMat4RotationZ(%struct.lib.kazmath.Mat4*, float)

; kmMat4* kmMat4RotationY(kmMat4* pOut, const kmScalar radians)
declare void @kmMat4RotationY(%struct.lib.kazmath.Mat4*, float)